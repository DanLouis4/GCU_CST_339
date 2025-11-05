// --- Live Preview for Add/Edit Restaurant Page Only ---
document.addEventListener("DOMContentLoaded", function () {

    // run only if hero-content has class 'update' (restaurant pages)
    const heroSection = document.querySelector(".hero-content.update");
    if (!heroSection) return; // skip on product pages

    const nameInput = document.getElementById("name");
    const addressInput = document.getElementById("address");
    const phoneInput = document.getElementById("phone");
    const descriptionInput = document.getElementById("description");
    const imageURLInput = document.getElementById("imageURL");

    const hero = document.querySelector(".hero");
    const heroTitle = document.getElementById("restaurantTitle");
    const heroSubtitle = document.getElementById("restaurantDescription");
    const heroAddress = document.getElementById("restaurantAddress");
    const preview = document.getElementById("imagePreview");

    // ✅ Update hero background + preview
    if (imageURLInput) {
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
    }

    // ✅ Update restaurant name live
    if (nameInput && heroTitle) {
        nameInput.addEventListener("input", function () {
            const value = nameInput.value.trim();
            heroTitle.textContent = value || "Add a New Restaurant";
        });
    }

    // ✅ Update address and phone line
    function updateDetails() {
        if (heroAddress) {
            const address = addressInput?.value.trim() || "123 Main St, City, State ZIP";
            const phone = phoneInput?.value.trim() || "(555) 123-4567";
            heroAddress.textContent = `${address} ★ ${phone}`;
        }
    }
    if (addressInput) addressInput.addEventListener("input", updateDetails);
    if (phoneInput) phoneInput.addEventListener("input", updateDetails);

    // ✅ Update restaurant description live
    if (descriptionInput && heroSubtitle) {
        descriptionInput.addEventListener("input", function () {
            const desc = descriptionInput.value.trim();
            heroSubtitle.textContent =
                desc || "Complete the details below to register your restaurant on Speed-E-Eats.";
        });
    }
});

// --- Simple image preview for product pages (no hero update) ---
function showImagePreview() {
    const url = document.getElementById("imageURL")?.value;
    const preview = document.getElementById("imagePreview");
    if (!preview) return;

    if (url && url.startsWith("http")) {
        preview.src = url;
    } else {
        preview.src = "/images/restaurant-placeholder.jpg";
    }
}
