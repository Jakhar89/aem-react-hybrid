module.exports = {
    presets: [
        [
            "@babel/preset-env",
            {
                targets: {
                    node: "current",
                },
                modules: false, // Keep ES modules for dynamic imports
            },
        ],
        "@babel/preset-react",
    ],
    plugins: [
        "@babel/plugin-proposal-class-properties",
        "@babel/plugin-proposal-object-rest-spread",
        "@babel/plugin-syntax-dynamic-import",
    ],
    env: {
        node: {
            presets: [
                [
                    "@babel/preset-env",
                    {
                        targets: {
                            node: "current",
                        },
                        modules: "commonjs", // Use CommonJS for Node.js
                    },
                ],
                "@babel/preset-react",
            ],
        },
    },
};
