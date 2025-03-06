// Function to load an external HTML file into the content area
function loadContent(page) {
    const content = document.getElementById("content");

    // Define file paths for each page
    let pageFiles = {
        products: "finished_products.ftlh",
        ingredients: "ingredients.ftlh",
        materials: "raw_materials.ftlh"
    };

    // Fetch and load the requested HTML file
    fetch(pageFiles[page])
        .then(response => response.text()) // Convert response to text
        .then(data => {
            content.innerHTML = data; // Insert the loaded HTML into the content div

            // Add fade-in effect
            content.classList.remove("fade-in");
            void content.offsetWidth; // Trigger reflow for animation
            content.classList.add("fade-in");
        })
        .catch(error => {
            content.innerHTML = "<h2>Error loading page.</h2><p>Please try again.</p>";
            console.error("Error loading content:", error);
        });
}
