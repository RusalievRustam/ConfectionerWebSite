<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Ingredients</title>
    <link rel="stylesheet" href="/styles.css">
    <script>
        function addIngredientField() {
            let container = document.getElementById("ingredients-container");

            let div = document.createElement("div");
            div.classList.add("ingredient-item");

            div.innerHTML = `
                <input type="text" name="name" placeholder="Ingredient Name" required>
                <input type="number" name="quantity" placeholder="Quantity" required>
                <button type="button" onclick="removeIngredientField(this)">❌</button>
            `;

            container.appendChild(div);
        }

        function removeIngredientField(button) {
            button.parentElement.remove();
        }

        function submitIngredients() {
            let ingredients = [];
            let productId = document.getElementById("productId").value; // Get from hidden input

            let ingredientItems = document.querySelectorAll(".ingredient-item");

            ingredientItems.forEach(item => {
                let ingredient = item.querySelector("input[name='name']").value;
                let quantity = item.querySelector("input[name='quantity']").value;

                ingredients.push({
                    ingredient,
                    quantity: parseFloat(quantity),
                    productId: productId // Include product ID
                });
            });

            fetch('/ingredients/save', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(ingredients)
            }).then(response => response.json())
            .then(data => {
                alert("Ingredients saved successfully!");
                window.location.href = "/ingredients/productId"; // Redirect to all ingredients page
            });
        }
    </script>
</head>
<body>
<#include "header.ftlh">
<h2>Add Ingredients</h2>

<!-- Hidden input field for productId -->
<input type="hidden" id="productId" value="${productId}">

<p>Adding ingredients for Product: <strong>${productName}</strong></p>

<div id="ingredients-container"></div>

<button type="button" onclick="addIngredientField()">➕ Add Ingredient</button>
<button type="button" onclick="submitIngredients()">✅ Save Ingredients</button>
<a href="/products">Назад</a>
</body>
</html>
