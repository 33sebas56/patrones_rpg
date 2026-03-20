let currentEventType = null;

const heroInfo = document.getElementById("heroInfo");
const messageBox = document.getElementById("messageBox");

const eventBox = document.getElementById("eventBox");
const eventTitle = document.getElementById("eventTitle");
const eventDescription = document.getElementById("eventDescription");
const eventChoice = document.getElementById("eventChoice");

document.getElementById("createHeroBtn").addEventListener("click", createHero);
document.getElementById("equipItemBtn").addEventListener("click", equipItem);
document.getElementById("applyBuffBtn").addEventListener("click", applyBuff);
document.getElementById("getEventBtn").addEventListener("click", getRandomEvent);
document.getElementById("resolveEventBtn").addEventListener("click", resolveEvent);
document.getElementById("battleBtn").addEventListener("click", simulateBattle);
document.getElementById("refreshHeroBtn").addEventListener("click", refreshHero);

async function createHero() {
    const name = document.getElementById("heroName").value.trim();
    const heroClassType = document.getElementById("heroClass").value;

    try {
        const response = await fetch("/api/heroes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                heroClassType: heroClassType
            })
        });

        const data = await response.json();
        handleResponse(response, data);

        renderHero(data);
        setMessage(`Héroe creado con éxito.\n\n${data.name} ya está listo para tomar malas decisiones.`);
    } catch (error) {
        showError(error);
    }
}

async function equipItem() {
    const itemType = document.getElementById("itemType").value;

    try {
        const response = await fetch("/api/heroes/equip", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                itemType: itemType
            })
        });

        const data = await response.json();
        handleResponse(response, data);

        renderHero(data);
        setMessage(`Item equipado.\n\nTu héroe ahora carga otra decisión financieramente irresponsable.`);
    } catch (error) {
        showError(error);
    }
}

async function applyBuff() {
    const buffType = document.getElementById("buffType").value;

    try {
        const response = await fetch("/api/heroes/buff", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                buffType: buffType
            })
        });

        const data = await response.json();
        handleResponse(response, data);

        renderHero(data);
        setMessage(`Efecto aplicado.\n\nNada dice estabilidad como apilar traumas y cafeína sobre una sola entidad.`);
    } catch (error) {
        showError(error);
    }
}

async function refreshHero() {
    try {
        const response = await fetch("/api/heroes/current");
        const data = await response.json();
        handleResponse(response, data);

        renderHero(data);
        setMessage("Estado del héroe actualizado.");
    } catch (error) {
        showError(error);
    }
}

async function getRandomEvent() {
    try {
        const response = await fetch("/api/events/random");
        const data = await response.json();
        handleResponse(response, data);

        currentEventType = data.eventType;

        eventTitle.textContent = data.title;
        eventDescription.textContent = data.description;

        eventChoice.innerHTML = "";

        data.availableChoices.forEach(choice => {
            const option = document.createElement("option");
            option.value = choice;
            option.textContent = translateChoice(choice);
            eventChoice.appendChild(option);
        });

        eventBox.classList.remove("hidden");
        setMessage("Evento cargado.\n\nTu destino acaba de depender de una decisión claramente improvisada.");
    } catch (error) {
        showError(error);
    }
}

async function resolveEvent() {
    if (!currentEventType) {
        setMessage("Primero debes obtener un evento. La improvisación también tiene límites.");
        return;
    }

    try {
        const response = await fetch("/api/events/resolve", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                eventType: currentEventType,
                choiceType: eventChoice.value
            })
        });

        const data = await response.json();
        handleResponse(response, data);

        setMessage(`Resultado del evento: ${data.title}\n\n${data.description}\n\nEfecto aplicado: ${data.appliedEffect}`);
        await refreshHeroSilently();
    } catch (error) {
        showError(error);
    }
}

async function simulateBattle() {
    const enemyName = document.getElementById("enemyType").value;

    try {
        const response = await fetch("/api/battles/simulate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                enemyName: enemyName
            })
        });

        const data = await response.json();
        handleResponse(response, data);

        setMessage(
            `Combate completado.\n\n` +
            `Héroe: ${data.heroName}\n` +
            `Enemigo: ${data.enemyName}\n` +
            `Ganador: ${data.winner}\n\n` +
            `${data.battleLog}`
        );
    } catch (error) {
        showError(error);
    }
}

async function refreshHeroSilently() {
    const response = await fetch("/api/heroes/current");
    const data = await response.json();

    if (response.ok) {
        renderHero(data);
    }
}

function renderHero(hero) {
    heroInfo.innerHTML = `
        <h3>${escapeHtml(hero.name)}</h3>
        <div class="description-box">${escapeHtml(hero.description)}</div>
        <div class="stat-list">
            <div class="stat-item"><strong>Health:</strong> ${hero.health}</div>
            <div class="stat-item"><strong>Attack:</strong> ${hero.attack}</div>
            <div class="stat-item"><strong>Defense:</strong> ${hero.defense}</div>
            <div class="stat-item"><strong>Speed:</strong> ${hero.speed}</div>
            <div class="stat-item"><strong>Crit Chance:</strong> ${hero.critChance}</div>
            <div class="stat-item"><strong>Luck:</strong> ${hero.luck}</div>
        </div>
    `;
}

function setMessage(text) {
    messageBox.textContent = text;
}

function translateChoice(choice) {
    const translations = {
        APPROACH: "Acercarte",
        KEEP_WALKING: "Seguir caminando",
        EAT_IT: "Comerlo",
        IGNORE_IT: "Ignorarlo",
        OPEN_IT: "Abrirlo",
        DELETE_IT: "Borrarlo",
        CHECK_IT: "Revisarlo",
        DENY_REALITY: "Negar la realidad"
    };

    return translations[choice] || choice;
}

function handleResponse(response, data) {
    if (!response.ok) {
        const message = data.message || "Algo salió mal";
        throw new Error(message);
    }
}

function showError(error) {
    setMessage(`Error:\n\n${error.message}`);
}

function escapeHtml(value) {
    if (value === null || value === undefined) {
        return "";
    }

    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}