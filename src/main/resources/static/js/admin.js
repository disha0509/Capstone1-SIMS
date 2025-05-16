function logout() {
    localStorage.removeItem("role"); // Clear the user's role
    window.location.href = "index.html"; // Redirect to login page
}