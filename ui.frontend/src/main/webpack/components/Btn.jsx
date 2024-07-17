import React from "react";
import { Button } from "@auspost/postmaster-react";

const Btn = (props) => {
  console.log(props);
  return (
    <>
      <Button variant="primary" size="medium">
        {props.text} anything
      </Button>
    </>
  );
};

export default Btn;
