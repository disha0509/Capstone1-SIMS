const LOW_STOCK_API = "http://localhost:8080/api/alerts/low-stock";
const EXPIRY_API = "http://localhost:8080/api/alerts/expiry";

// Function to Fetch Low Stock Alerts
async function fetchLowStockAlerts() {
    try {
        const response = await fetch(LOW_STOCK_API);
        const items = await response.json();
        populateTable(items);
    } catch (error) {
        console.error("Error fetching low stock alerts:", error);
    }
}

// Function to Fetch Expiry Alerts
async function fetchExpiryAlerts() {
    try {
        const response = await fetch(EXPIRY_API);
        const items = await response.json();
        populateTable(items);
    } catch (error) {
        console.error("Error fetching expiry alerts:", error);
    }
}

// Function to Populate the Table with Data
function populateTable(items) {
    const tableBody = document.getElementById("alertsTable");
    tableBody.innerHTML = ""; // Clear existing rows

    items.forEach(item => {
        const row = `
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>${item.expiryDate || "N/A"}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}