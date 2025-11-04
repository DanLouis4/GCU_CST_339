// --- Live Preview for Add Restaurant Page ---

document.addEventListener("DOMContentLoaded", function () {
    const nameInput = document.getElementById("name");
    const addressInput = document.getElementById("address");
    const phoneInput = document.getElementById("phone");
    const descriptionInput = document.getElementById("description");
    const imageURLInput = document.getElementById("imageURL");

    const hero = document.querySelector(".hero");
    const heroTitle = document.querySelector(".hero-content h1");
    const heroSubtitle = document.querySelector(".hero-content p");
    const heroAddress = document.getElementById("restaurantAddress");
    const heroPhone = document.getElementById("restaurantPhone");
    const preview = document.getElementById("imagePreview");

    // Update hero background image and preview thumbnail
    imageURLInput.addEventListener("input", function () {
        const url = imageURLInput.value.trim();
        if (url && url.startsWith("http")) {
            hero.style.backgroundImage = `url('${url}')`;
            preview.src = url;
        } else {
            hero.style.backgroundImage = "url('/images/restaurant-placeholder.jpg')";
            preview.src = "/images/restaurant-placeholder.jpg";
        }
    });

    // Update restaurant name live
    nameInput.addEventListener("input", function () {
        const value = nameInput.value.trim();
        heroTitle.textContent = value || "Add a New Restaurant";
    });

    // Update address and phone below hero
    function updateDetails() {
        const address = addressInput.value.trim() || "123 Main St, City, State ZIP";
        const phone = phoneInput.value.trim() || "(555) 123-4567";
        heroAddress.textContent = `${address} ★ ${phone}`;
    }
    addressInput.addEventListener("input", updateDetails);
    phoneInput.addEventListener("input", updateDetails);

    // Update hero subtitle (description)
    descriptionInput.addEventListener("input", function () {
        const desc = descriptionInput.value.trim();
        heroSubtitle.textContent = desc || "Complete the details below to register your restaurant on Speed-E-Eats.";
    });
});

// --- Simple image preview function for form field ---
function showImagePreview() {
    const url = document.getElementById("imageURL").value;
    const preview = document.getElementById("imagePreview");
    if (url && url.startsWith("http")) {
        preview.src = url;
    } else {
        preview.src = "/images/restaurant-placeholder.jpg";
    }
}
