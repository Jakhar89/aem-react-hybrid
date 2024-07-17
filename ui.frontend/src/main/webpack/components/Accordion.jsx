import React from "react";
import { Accordion, Text } from "@auspost/postmaster-react";

const AemAccordion = (props) => {
  const args = {
    title: `${props.title || "Title"}`,
    children: <Text variant="body">{props.text || "Loren Ipsum"}</Text>,
    icon: null,
  };
  return <Accordion {...args} />;
};

export default AemAccordion;
