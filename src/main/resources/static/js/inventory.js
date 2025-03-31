const INVENTORY_API = "http://localhost:8080/api/inventory/view";
const SELL_API = "http://localhost:8080/api/inventory/sell";

// Fetch inventory data and populate the table
async function fetchInventory() {
    const response = await fetch(INVENTORY_API);
    const items = await response.json();

    const table = document.getElementById("inventoryTable");
    table.innerHTML = "";

    items.forEach(item => {
        const row = `<tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.quantity}</td>
        </tr>`;
        table.innerHTML += row;
    });
}

// Handle selling an item
async function sellItem(event) {
    event.preventDefault();
    const id = document.getElementById("sellItemId").value;
    const quantity = document.getElementById("sellItemQuantity").value;

    try {
        const response = await fetch(`${SELL_API}/${id}?quantity=${quantity}`, {
            method: "POST",
        });

        if (response.ok) {
            alert("Item sold successfully!");
            fetchInventory(); // Refresh the inventory table
        } else {
            const error = await response.text();
            alert(`Error: ${error}`);
        }
    } catch (error) {
        console.error("Error selling item:", error);
    }
}

// Handle logout
function logout() {
    alert("Logging out...");
    window.location.href = "index.html"; // Redirect to login
}

// Redirect to About Us page
function showAboutUs() {
    window.location.href = "aboutus.html"; // Redirect to About Us page
}

// Load inventory when page loads
window.onload = fetchInventory;