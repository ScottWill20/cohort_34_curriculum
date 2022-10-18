import React from 'react';
import { render } from '@testing-library/react';
import CoinPanelFunc from './CoinPanelFunc';

test("should render proper markup", () => {
    const result = render(<CoinPanelFunc className="col" amount={0.25} maxClicks={10} />);

    // Can match by exact text.
    const btn = result.getByText("Insert 25\xA2");

    // These matchers are not core Jest.
    // They're in https://github.com/testing-library/jest-dom.
    expect(btn).toBeInTheDocument();
    expect(btn).toHaveClass("btn");

    // Can match by regular expression.
    const contributionDiv = result.getByText(/Contributed: \$.*/);
    expect(contributionDiv).toBeInTheDocument();

    const percentDiv = result.getByText("0% of total");
    expect(percentDiv).toBeInTheDocument();

    // There are many ways to assert DOM facts.
    expect(result.baseElement.querySelectorAll(".col").length).toBe(1);
});