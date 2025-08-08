import React from "react";

const Btn = (props) => {
    console.log(props);
    return (
        <>
            <button variant='primary' size='medium' id={props.uniqID}>
                {props.btnText} anything
            </button>
        </>
    );
};

export default Btn;
