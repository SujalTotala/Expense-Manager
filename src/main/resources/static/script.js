const baseUrl = "http://localhost:8080/api";

window.onload = () => {
    loadParticipants();
    loadExpenses();
};

// ADD PARTICIPANT
function addParticipant() {
    const name = document.getElementById("name").value;

    fetch(baseUrl + "/participants", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name })
    }).then(() => {
        document.getElementById("name").value = "";
        loadParticipants();
    });
}

// LOAD PARTICIPANTS
function loadParticipants() {
    fetch(baseUrl + "/participants")
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("participantList");
            const paidBy = document.getElementById("paidBy");
            const participants = document.getElementById("participants");

            list.innerHTML = "";
            paidBy.innerHTML = "";
            participants.innerHTML = "";

            data.forEach(p => {
                const li = document.createElement("li");

                li.innerHTML = `
                    ${p.name}
                    <div>
                        <button onclick="editParticipant(${p.id}, '${p.name}')">Edit</button>
                        <button onclick="deleteParticipant(${p.id})">Delete</button>
                    </div>
                `;

                list.appendChild(li);

                paidBy.innerHTML += `<option value="${p.id}">${p.name}</option>`;
                participants.innerHTML += `<option value="${p.id}">${p.name}</option>`;
            });
        });
}

// DELETE PARTICIPANT
function deleteParticipant(id) {
    fetch(baseUrl + "/participants/" + id, {
        method: "DELETE"
    }).then(loadParticipants);
}

// EDIT PARTICIPANT
function editParticipant(id, name) {
    const newName = prompt("Edit name:", name);
    if (!newName) return;

    fetch(baseUrl + "/participants/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: newName })
    }).then(loadParticipants);
}

// ADD EXPENSE
function addExpense() {
    const title = document.getElementById("title").value;
    const amount = parseFloat(document.getElementById("amount").value);
    const category = document.getElementById("category").value;
    const paidById = parseInt(document.getElementById("paidBy").value);

    const participantIds = Array.from(
        document.getElementById("participants").selectedOptions
    ).map(opt => parseInt(opt.value));

    fetch(baseUrl + "/expenses/split", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            title,
            amount,
            category,
            paidById,
            participantIds
        })
    }).then(() => loadExpenses());
}

// LOAD EXPENSES
function loadExpenses() {
    fetch(baseUrl + "/expenses")
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("expenseList");
            list.innerHTML = "";

            data.forEach(e => {
                const li = document.createElement("li");

                li.innerHTML = `
                    ${e.title} - ₹${e.amount}
                    <div>
                        <button onclick="editExpense(${e.id})">Edit</button>
                        <button onclick="deleteExpense(${e.id})">Delete</button>
                    </div>
                `;

                list.appendChild(li);
            });
        });
}

// DELETE EXPENSE
function deleteExpense(id) {
    fetch(baseUrl + "/expenses/" + id, {
        method: "DELETE"
    }).then(loadExpenses);
}

// EDIT EXPENSE
function editExpense(id) {
    const title = prompt("Title:");
    const amount = prompt("Amount:");
    const category = prompt("Category:");

    fetch(baseUrl + "/expenses/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, amount, category })
    }).then(loadExpenses);
}

// GET BALANCES
function getBalances() {
    fetch(baseUrl + "/expenses/balances")
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("balances");
            list.innerHTML = "";

            data.forEach(b => {
                const li = document.createElement("li");

                li.textContent = `${b.fromParticipant} owes ${b.toParticipant} ₹${b.amount}`;

                list.appendChild(li);
            });
        });
}

// SETTLE UP
function settleUp() {
    fetch(baseUrl + "/expenses/settle", {
        method: "DELETE"
    }).then(() => {
        document.getElementById("balances").innerHTML = "";
        alert("All balances settled");
    });
}