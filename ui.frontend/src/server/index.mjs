// server.js
import express from "express";
import React from "react";
import ReactDOMServer from "react-dom/server";
import { COMPONENTS } from "../main/webpack/site/componentDirectory.mjs";

const app = express();
app.use(express.json());

async function MyComponent(props) {
    const uniqID = `${Date.now().toString(36)}-${Math.random()
        .toString(36)
        .replace(/[.]/g, "")}`;

    const componentImporter = COMPONENTS[props.resourceType];
    console.log("SSR props:", props);
    console.log("Available components:", Object.keys(COMPONENTS));

    if (componentImporter !== undefined) {
        try {
            // Call the function to get the dynamic import promise
            const componentModule = await componentImporter();
            console.log("Component module loaded:", componentModule);
            let Component = componentModule.default;

            // If the default export is still an object with a default property, go deeper
            while (
                Component &&
                typeof Component === "object" &&
                Component.default
            ) {
                Component = Component.default;
            }
            console.log("Loaded component:", Component);
            console.log("Loaded component:", componentModule.default);
            const componentProps = { ...props, uniqID: uniqID };
            return React.createElement(Component, componentProps);
        } catch (error) {
            console.error("Error loading component:", error);
            return React.createElement(
                "div",
                null,
                "Error loading component: " + props.resourceType
            );
        }
    } else {
        return React.createElement(
            "div",
            null,
            "Component not found: " + props.resourceType
        );
    }
}

app.post("/ssr", async (req, res) => {
    try {
        const data = req.body;
        console.log("SSR request data:", data);

        const element = await MyComponent(data);
        console.log("SSR element:", element);
        const html = ReactDOMServer.renderToString(element);

        res.json({ html });
    } catch (error) {
        console.error("SSR Error:", error);
        res.status(500).json({
            html: "<!-- SSR Error: " + error.message + " -->",
        });
    }
});

const PORT = 4200;
app.listen(PORT, () => console.log(`SSR server listening on port ${PORT}`));
