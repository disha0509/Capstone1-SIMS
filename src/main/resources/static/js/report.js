const API_BASE_URL = "http://localhost:8080/api/reports";

// Fetch Daily Report
async function fetchDailyReport() {
    try {
        const response = await fetch(`${API_BASE_URL}/daily`);
        const reportData = await response.json();
        populateTable(reportData);
    } catch (error) {
        console.error("Error fetching daily report:", error);
    }
}

// Fetch Weekly Report
async function fetchWeeklyReport() {
    try {
        const response = await fetch(`${API_BASE_URL}/weekly`);
        const reportData = await response.json();
        populateTable(reportData);
    } catch (error) {
        console.error("Error fetching weekly report:", error);
    }
}

// Export Daily Report
function exportDailyReport() {
    window.location.href = `${API_BASE_URL}/export-daily`;
}

// Export Weekly Report
function exportWeeklyReport() {
    window.location.href = `${API_BASE_URL}/export-weekly`;
}

// Populate Table with Report Data
function populateTable(reportData) {
    const tableBody = document.getElementById("reportTable");
    tableBody.innerHTML = ""; // Clear existing rows

    reportData.forEach(item => {
        const row = `
            <tr>
                <td>${item.id}</td>
                <td>${item.inventoryItem.id}</td>
                <td>${item.movementType}</td>
                <td>${item.quantity}</td>
                <td>${item.timestamp}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}