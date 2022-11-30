import httpRequest from "~/utils/httpRequest";

export const getWeatherNoExpired = async () => {
    try {
        const res = await httpRequest.get("/weather/getCurrentNoExpired")
        return res.data;
    } catch (error) {
        console.log(error);
    }
}




