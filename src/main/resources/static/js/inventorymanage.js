const API_BASE_URL = "http://localhost:8080/api/inventory";

// Show Add Item Form
function showAddItemForm() {
    hideAllForms();
    document.getElementById("addItemForm").style.display = "block";
}

// Show Edit Item Form
function showEditItemForm() {
    hideAllForms();
    document.getElementById("editItemForm").style.display = "block";
}

// Show Delete Item Form
function showDeleteItemForm() {
    hideAllForms();
    document.getElementById("deleteItemForm").style.display = "block";
}

// Hide All Forms
function hideAllForms() {
    document.getElementById("addItemForm").style.display = "none";
    document.getElementById("editItemForm").style.display = "none";
    document.getElementById("deleteItemForm").style.display = "none";
}

// Fetch Inventory
async function fetchInventory() {
    try {
        const response = await fetch(`${API_BASE_URL}/view`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        const items = await response.json();
        populateTable(items);
    } catch (error) {
        console.error("Error fetching inventory:", error);
    }
}

// Add Item
async function addItem(event) {
    event.preventDefault();
    const name = document.getElementById("addItemName").value;
    const quantity = document.getElementById("addItemQuantity").value;
    const category = document.getElementById("addItemCategory").value;
    const expiryDate = document.getElementById("addItemExpiryDate").value;

    try {
        const response = await fetch(`${API_BASE_URL}/add`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, quantity, category, expiryDate }),
        });
        if (response.ok) {
            alert("Item added successfully!");
            fetchInventory();
        }
    } catch (error) {
        console.error("Error adding item:", error);
    }
}

// Edit Item
async function editItem(event) {
    event.preventDefault();
    const id = document.getElementById("editItemId").value;
    const name = document.getElementById("editItemName").value;
    const quantity = document.getElementById("editItemQuantity").value;

    try {
        const response = await fetch(`${API_BASE_URL}/edit/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, quantity }),
        });
        if (response.ok) {
            alert("Item edited successfully!");
            fetchInventory();
        }
    } catch (error) {
        console.error("Error editing item:", error);
    }
}

// Delete Item
async function deleteItem(event) {
    event.preventDefault();
    const id = document.getElementById("deleteItemId").value;

    try {
        const response = await fetch(`${API_BASE_URL}/delete/${id}`, {
            method: "DELETE",
        });
        if (response.ok) {
            alert("Item deleted successfully!");
            fetchInventory();
        }
    } catch (error) {
        console.error("Error deleting item:", error);
    }
}

// Populate Table
function populateTable(items) {
    const tableBody = document.getElementById("inventoryTable");
    tableBody.innerHTML = ""; // Clear existing rows

    items.forEach(item => {
        const row = `
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

// Load inventory when page loads
window.onload = fetchInventory;