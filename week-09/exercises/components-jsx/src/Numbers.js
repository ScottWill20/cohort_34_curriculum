function Numbers({min = 1, max = 10}) {
    const numbersToDisplay = [];
    const numbers = [1,2,3,4,5,6,7,8,9,10];
    const groceries = ['eggs', 'milk', 'bananas', 'dog food', 'apples'];

    for (let counter = min; counter <= max; counter++) {
        numbersToDisplay.push(counter);
    }

    function renderNumbers(arr) {
        return (
            <>
                {arr.map( n =>
                <li>{n}</li>
                )}
            </>
        );
    }


    return (
        <>
            <ul>{renderNumbers(numbersToDisplay)}</ul>
            <ul>{renderNumbers(numbers)}</ul>
            <ul>{renderNumbers(groceries)}</ul>
        </>
    );
}

export default Numbers;