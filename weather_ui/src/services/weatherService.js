import httpRequest from "~/utils/httpRequest";

export const getWeatherNoExpired = async () => {
    try {
        const res = await httpRequest.get("/weather/getCurrentNoExpired")
        return res.data;
    } catch (error) {
        console.log(error);
    }
}



export const getAll = async () => {
    try {
        const res = await httpRequest.get("/weather/getAll")
        return res.data;
    } catch (error) {
        console.log(error);
    }
}
