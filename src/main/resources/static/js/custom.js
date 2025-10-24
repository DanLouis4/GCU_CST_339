function showImagePreview() {
    const url = document.getElementById('imageURL').value;
    const preview = document.getElementById('imagePreview');
    if (url && url.startsWith('http')) {
        preview.src = url;
    } else {
        preview.src = '#';
    }
}
