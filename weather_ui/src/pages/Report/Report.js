import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper} from '@mui/material';
import {useEffect, useState} from "react";
import * as weatherService from "~/services/weatherService"

import style from "./Report.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(style);

function Report() {
    const [weathers, setWeathers] = useState([]);


    useEffect(() => {
        const fetchApi = async () => {
            const result = await weatherService.getWeatherNoExpired();
            setWeathers(result)
        }
        fetchApi();
    }, [])
    console.log(weathers)
    return (
        <div className="container">
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Natural Key</TableCell>
                            <TableCell align="right">Ngày dự báo</TableCell>
                            <TableCell align="right">Tỉnh/Thành phố</TableCell>
                            <TableCell align="right">Nhiệt độ thấp</TableCell>
                            <TableCell align="right">Nhiệt độ cao</TableCell>
                            <TableCell align="right">Tốc độ gió</TableCell>
                            <TableCell align="right">Độ ẩm</TableCell>
                            <TableCell align="right">Trạng thái</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {weathers.map((row, index) => (
                            <TableRow
                                key={index}
                            >
                                <TableCell scope="row">
                                    {row.natural_key}
                                </TableCell>
                                <TableCell align="right">{row.forecast_date.date}</TableCell>
                                <TableCell align="right">{row.province.name}</TableCell>
                                <TableCell align="right">{row.min_temp}°</TableCell>
                                <TableCell align="right">{row.max_temp}°</TableCell>
                                <TableCell align="right">{row.wind_speed}km/giờ</TableCell>
                                <TableCell align="right">{row.humidity}%</TableCell>
                                <TableCell
                                    align="right"
                                    className={cx((row.expired_date == 1462) ? ('green') : ('red'))}>{(row.expired_date == 1462) ? "Hoạt đông" : 'Hết hạn'}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

        </div>
    )


}

export default Report;