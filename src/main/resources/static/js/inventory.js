const INVENTORY_API = "http://localhost:8080/api/inventory/view";
const SELL_API = "http://localhost:8080/api/inventory/sell";

// Fetch inventory data and populate the table
async function fetchInventory() {
    try {
        const response = await fetch(INVENTORY_API);

        if (!response.ok) {
            throw new Error(`Failed to fetch inventory: ${response.status} ${response.statusText}`);
        }

        const items = await response.json();

        const table = document.getElementById("inventoryTable");
        table.innerHTML = ""; // Clear the table before adding new rows

        items.forEach(item => {
            const row = `<tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
            </tr>`;
            table.innerHTML += row;
        });
    } catch (error) {
        console.error("Error fetching inventory:", error);
        alert("Failed to load inventory. Please try again later.");
    }
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
    localStorage.removeItem("role"); // Clear the user's role
    window.location.href = "index.html"; // Redirect to login page
}

// Redirect to About Us page
function showAboutUs() {
    window.location.href = "aboutus.html"; // Redirect to About Us page
}

// Load inventory when page loads
window.onload = fetchInventory;