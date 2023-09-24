//Function to prevent users entering negative numbers in the Product quantity
    function preventNegativeInput(input) {
    if (input.value < 0) {
    input.value = 0;
}
}

function searchCategories() {
    let input = document.getElementById('categorySearchInput');
    let filter = input.value.toLowerCase();
    let categories = document.getElementsByClassName('card');

    for (let category of categories) {
        let categoryName = category.querySelector('.card-title').textContent.toLowerCase();
        category.style.display = categoryName.includes(filter) ? '' : 'none';
    }
}

