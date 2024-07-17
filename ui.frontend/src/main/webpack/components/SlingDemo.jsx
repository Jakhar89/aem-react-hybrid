import React from "react";

const SlingDemo = (props) => {
  const attr = JSON.parse(props.attribute);
  return (
    <>
      <h1>demo Content {attr.slingTitle || "World"}!</h1>
      {attr.slingCheck && <h2>Checkbox is true</h2>}
    </>
  );
};

export default SlingDemo;
