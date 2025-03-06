<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fixed Header with Dynamic Content</title>
    <link rel="stylesheet" href="header_style.css">
    <script>
    function loadContent(page) {
        const content = document.getElementById("content");

        // Define file paths for each page
        let pageFiles = {
            products: "href='/products'",
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
    </script>
</head>
<body>

    <!-- Fixed Header -->
    <header>
        <div class="logo">My Website</div>
        <nav>
            <button class="menu-btn">Menu ▼</button>
            <ul class="dropdown">
                <li><a href="#" onclick="loadContent('products')">Products</a></li>
                <li><a href="#" onclick="loadContent('ingredients')">Ingredients</a></li>
                <li><a href="#" onclick="loadContent('materials')">Materials</a></li>
            </ul>
        </nav>
    </header>

    <!-- Content Section -->
    <main id="content">
        <h2>Welcome!</h2>
        <p>Select a category from the menu to see more details.</p>
    </main>

</body>
</html>
