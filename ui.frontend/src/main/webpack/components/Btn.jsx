import React from "react";
import { Button } from "@auspost/postmaster-react";

const Btn = (props) => {
  const regex = /variant-/i;
  const getVariant = props.classes.split(" ").filter((wrd) => regex.test(wrd));
  const variantUsed =
    getVariant.length > 0 ? getVariant[0].replace(regex, "") : "primary";
  return (
    <>
      <Button variant={variantUsed} size="medium">
        {props.text} anything
      </Button>
    </>
  );
};

export default Btn;
