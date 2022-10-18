import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders Piggy Bank header', () => {
  const { getByText } = render(<App />);
  const h1 = getByText(/piggy bank/i);
  expect(h1).toBeInTheDocument();
});

test("expected outcome", () => {
  // Arrange, Act, Assert
});

test("2 + 2 equals 4", () => {
  // Arrange
  const firstOperand = 2;
  const secondOperand = 2;
  // Act
  const actual = firstOperand + secondOperand;
  // Assert
  const expected = 4;
  expect(actual).toBe(expected);
});

test('renders header, 3 .piggy-bank divs, and 9 buttons', () => {
  // React-specific helper that renders
  // a component into jsdom's DOM.
  // The return values is a wrapped jsdom element.
  const result = render(<App />);

  // Use the React helper API
  const h1 = result.getByText(/piggy bank/i);
  expect(h1).toBeInTheDocument();

  // Grab the jdsom base element.
  const dom = result.baseElement;

  // Now we can use standard DOM API like querySelectorAll.
  const piggyBankDivs = dom.querySelectorAll(".piggy-bank");
  expect(piggyBankDivs.length).toBe(3);

  const buttons = dom.querySelectorAll("button");
  expect(buttons.length).toBe(9);

  // verify the first button
  expect(buttons[0].innerHTML).toBe("Insert 25\xA2");
  expect(buttons[0].className).toBe("btn");
});
