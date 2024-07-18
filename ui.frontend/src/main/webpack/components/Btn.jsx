import React from "react";
//import { Button } from "@auspost/postmaster-react";

const Btn = (props) => {
  console.log(props);
  return (
    <>
      <button variant="primary" size="medium">
        {props.text} anything
      </button>
    </>
  );
};

export default Btn;
