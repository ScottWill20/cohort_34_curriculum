import React, { useState } from 'react';

// 1. Destructure props with default values.
function CoinPanel({ className, amount = 0.25, maxClicks = 10 }) {

    // 2. Track clicks with useState
    const [clicks, setClicks] = useState(0);

    // 3. Convenience variables.
    const pennies = (amount * 100).toFixed(0);
    const contributed = (amount * clicks).toFixed(2);

    return (
        <div className={className}>
            {/* 4. Inline event listener that sets clicks to one bigger than the current. */}
            <button className="btn" onClick={() => setClicks(clicks + 1)} disabled={clicks >= maxClicks}>
                {clicks >= maxClicks ?
                    `${pennies}\xA2 - clicked ${maxClicks} times`
                    : `Insert ${pennies}\xA2`
                }
            </button>
            <div>Contributed: ${contributed}</div>
            <div>0% of total</div>{/* 5. TODO */}
        </div>
    );
}

export default CoinPanel;