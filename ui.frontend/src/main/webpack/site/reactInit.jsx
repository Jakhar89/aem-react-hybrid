import React, { lazy, Suspense } from "react";
import { createRoot, hydrateRoot } from "react-dom/client";
import { COMPONENTS } from "./componentDirectory.mjs";

class ReactComponent extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        let props = JSON.parse(this.dataset.attribute || "{}");
        console.log("Client props:", props);

        let checkElement = document.getElementById(props.uuid);
        console.log("Existing element:", checkElement);

        const ComponentImporter = COMPONENTS[props.resourceType];

        if (ComponentImporter !== undefined) {
            // Load component dynamically
            ComponentImporter()
                .then((componentModule) => {
                    const Component = componentModule.default;

                    const componentElement = <Component {...props} />;

                    if (checkElement.innerHTML.trim() !== "") {
                        // Existing content - hydrate
                        console.log("Hydrating existing component");
                        hydrateRoot(this, componentElement);
                    } else {
                        // No existing content - render fresh
                        console.log("Rendering fresh component");
                        createRoot(this).render(componentElement);
                    }
                })
                .catch((error) => {
                    console.error("Failed to load component:", error);
                });
        }
    }
}

customElements.define("react-component", ReactComponent);
