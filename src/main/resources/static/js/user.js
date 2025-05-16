function loadPage(page) {
    // Hide the navigation section and header
    document.querySelector(".dashboard-nav").style.display = "none";
    document.querySelector(".dashboard-header").style.display = "none";

    // Load the requested page into the main-content section
    fetch(page)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Page not found");
            }
            return response.text();
        })
        .then((html) => {
            document.getElementById("main-content").innerHTML = html;
        })
        .catch((error) => {
            console.error("Error loading page:", error);
            document.getElementById("main-content").innerHTML = "<p>Error loading page. Please try again later.</p>";
        });
}

function showDashboard() {
    // Show the navigation section and header
    document.querySelector(".dashboard-nav").style.display = "flex";
    document.querySelector(".dashboard-header").style.display = "block";

    // Reset the main-content section
    document.getElementById("main-content").innerHTML = "<p>Welcome to your SIMS dashboard. Click a section above to get started.</p>";
}

function logout() {
    localStorage.removeItem("role"); // Clear the user's role
    window.location.href = "index.html"; // Redirect to login page
}