import React from "react";

const Btn = (props) => {
    console.log(props);

    const handleClick = () => {
        console.log("click working");
    };

    return <button onClick={handleClick}>{props.btnText}</button>;
};

export default Btn;
