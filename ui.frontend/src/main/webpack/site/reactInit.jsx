import React, { lazy, Suspense } from "react";
import { createRoot } from "react-dom";

const COMPONENTS = {
  demo: lazy(() => import("../components/Demo.jsx")),
  btn: lazy(() => import("../components/Btn.jsx")),
  accordion: lazy(() => import("../components/Accordion.jsx")),
  slingDemo: lazy(() => import("../components/SlingDemo.jsx")),
};

class ReactComponent extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const props = this.dataset;
    const Component = COMPONENTS[props.component];

    if (Component !== undefined) {
      createRoot(this).render(
        <Suspense fallback={null}>
          <Component {...props} />
        </Suspense>
      );
    }
  }
}

customElements.define("react-component", ReactComponent);
