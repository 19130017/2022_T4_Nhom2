//config
import config from "~/config";

//layouts

//pages
import Report from "~/pages/Report";
import Home from "~/pages/Home";

const publicRoutes = [
    {
        path: config.routes.report,
        component: Report
    },
    {
        path: config.routes.home,
        component: Home
    },

]

const privateRoutes = [];

export {privateRoutes, publicRoutes}