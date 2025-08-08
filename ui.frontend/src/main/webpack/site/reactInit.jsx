import React, { lazy, Suspense } from "react";
import { createRoot, hydrateRoot } from "react-dom/client";
import { COMPONENTS } from "./componentDirectory.mjs";

class ReactComponent extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        const uniqID = `${Date.now().toString(36)}-${Math.random()
            .toString(36)
            .replace(/[.]/g, "")}`;

        let props = JSON.parse(this.dataset.attribute || "{}");
        console.log("Client props:", props);

        let checkElement = document.getElementById(this?.firstChild?.id);
        console.log("Existing element:", checkElement);

        const ComponentImporter = COMPONENTS[props.resourceType];

        if (ComponentImporter !== undefined) {
            props = { ...props, uniqID: uniqID };

            // Load component dynamically
            ComponentImporter()
                .then((componentModule) => {
                    const Component = componentModule.default;

                    const componentElement = (
                        <Suspense fallback={<div>Loading...</div>}>
                            <Component {...props} />
                        </Suspense>
                    );

                    if (checkElement == null) {
                        // No existing content - render fresh
                        console.log("Rendering fresh component");
                        createRoot(this).render(componentElement);
                    } else {
                        // Existing content - hydrate
                        console.log("Hydrating existing component");
                        hydrateRoot(this, componentElement);
                    }
                })
                .catch((error) => {
                    console.error("Failed to load component:", error);
                });
        }
    }
}

customElements.define("react-component", ReactComponent);
