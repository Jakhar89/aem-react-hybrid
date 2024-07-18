import React from "react";
//import { Accordion, Text } from "@auspost/postmaster-react";

const AemAccordion = (props) => {
  const args = {
    title: `${props.title || "Title"}`,
    children: <Text variant="body">{props.text || "Loren Ipsum"}</Text>,
    icon: null,
  };
  return <div>{props.title}</div>;
};

export default AemAccordion;
