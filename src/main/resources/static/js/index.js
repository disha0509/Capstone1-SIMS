const API_URL = "http://localhost:8080/api/users";

// Toggle between login and register forms
function showForm(type) {
    document.getElementById("loginForm").style.display = type === "login" ? "block" : "none";
    document.getElementById("registerForm").style.display = type === "register" ? "block" : "none";
    document.getElementById("message").innerText = ""; // Clear any messages
}

// Handle login logic
async function handleLogin() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const response = await fetch(`${API_URL}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password }),
        });

        const data = await response.json();

        if (response.ok) {
            // Store the user's role in localStorage
            localStorage.setItem("role", data.role);

            // Redirect based on role
            if (data.role === "USER") {
                window.location.href = "user.html"; // Redirect to Inventory page
            } else if (data.role === "ADMIN") {
                window.location.href = "admin.html"; // Redirect to Admin page
            }
        } else {
            document.getElementById("message").innerText = "Login failed! Check credentials.";
        }
    } catch (error) {
        console.error("Error during login:", error);
        document.getElementById("message").innerText = "Login failed! Check credentials.";
    }
}

// Handle registration logic
async function handleRegister() {
    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;
    const role = document.getElementById("registerRole").value;

    try {
        const response = await fetch(`${API_URL}/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password, role }),
        });

        const data = await response.json();

        if (response.ok) {
            // Show success message
            document.getElementById("message").innerText = "Registration successful, now you can log in.";
            document.getElementById("message").classList.remove("text-danger");
            document.getElementById("message").classList.add("text-success");

            // Switch to login form
            showForm("login");
        } else {
            // Show error message
            document.getElementById("message").innerText = "Registration failed! Try again.";
            document.getElementById("message").classList.remove("text-success");
            document.getElementById("message").classList.add("text-danger");
        }
    } catch (error) {
        console.error("Error during registration:", error);
        document.getElementById("message").innerText = "An error occurred. Please try again.";
        document.getElementById("message").classList.remove("text-success");
        document.getElementById("message").classList.add("text-danger");
    }
}