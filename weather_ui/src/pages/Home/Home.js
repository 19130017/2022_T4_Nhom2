import {useEffect, useState} from "react";
import * as weatherService from "~/services/weatherService"
import CardWeather from "~/components/CardWeather"
import LocationIcon from '@mui/icons-material/PersonPinCircle';
import {BsSunrise, BsSunset, BsFillSunFill, BsThermometerSun, BsWind} from 'react-icons/bs';
import {WiHumidity} from 'react-icons/wi'
import {AiTwotoneEye} from 'react-icons/ai'
import {GrLocationPin} from 'react-icons/gr'


import style from "./Home.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(style);

function Home() {
    const [weathers, setWeathers] = useState([]);


    useEffect(() => {
        const fetchApi = async () => {
            const result = await weatherService.getWeatherNoExpired();
            setWeathers(result)
        }
        fetchApi();
    }, [])
    return (
        <section className={cx("section-weather")}>
            <div className="container">
                <div className="row">
                    <div className="col-12 col-md-8 order-1 order-sm-0">
                        <h3 className={cx('title')}>DỰ BÁO THỜI TIẾT CÁC TỈNH/THÀNH PHỐ</h3>
                        <div className='row'>
                            {
                                weathers.map((weather, index) => (
                                    <div key={index} className={cx('col-6 col-md-3')}>
                                        <div className={cx('forecast')}>
                                            <CardWeather data={weather}/>
                                        </div>
                                    </div>
                                ))
                            }
                        </div>
                    </div>
                    <div className="col-12 col-md-4 order-0 order-sm-1">
                        <div className={cx('current-location')}>
                            <div className={cx('location-name')}>
                                <span className={cx('location-name-icon')}>
                                    <LocationIcon sx={{width: '2rem', height: '2rem'}}/>
                                </span>
                                <span className={cx('location-name-main')}>Hà Nội</span>
                            </div>
                            <div className={cx('location-data')}>
                                <div className={cx('location-data-summary')}>
                                    <div className={cx('overview-current')}>
                                        <img src="https://data.thoitiet.vn/weather/icons/02d@2x.png" alt="description"/>
                                        <span
                                            className={cx('current-temperature')}>31°</span>
                                        <div className={cx('util-group')}>
                                            <p>C</p>
                                            <p>F</p>
                                        </div>
                                    </div>
                                    <div className={cx('overview-caption')}>
                                        <p className={cx('overview-caption-item')}>Mây rải rác</p>
                                        <p className={cx('overview-caption-item')}>Cảm giác
                                            như 31°.</p>
                                    </div>
                                    <div className={cx('weather-detail')}>
                                        <h2 className={cx('weather-detail-title')}>Dự báo thời tiết hôm nay</h2>
                                        <div className="d-flex align-items-center">
                                            <div className={cx('avatar')}>
                                                <span className={cx('weather-icon')}>
                                                    <BsFillSunFill style={{height: '2rem', width: '2rem'}}/>
                                                </span>
                                            </div>
                                            <div className="flex-1">
                                                <h5>Mặt trời mọc/lặn</h5>
                                            </div>
                                            <div className="d-flex ms-auto align-items-center">
                                                <div className={cx('weather-sun')}>
                                                    <span className="me-2"><BsSunrise/>6:12 </span>
                                                    <span><BsSunset/>5:21 </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className={cx('separator-dashed')}></div>

                                        <div className="d-flex align-items-center">
                                            <div className={cx('avatar')}>
                                                <span className={cx('weather-icon')}>
                                                    <BsThermometerSun style={{height: '2rem', width: '2rem'}}/>
                                                </span>
                                            </div>
                                            <div className="flex-1">
                                                <h5>Thấp/Cao</h5>
                                            </div>
                                            <div className="d-flex ms-auto align-items-center">
                                                <div className={cx('weather-sun')}>
                                                    <span>19°/31°</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className={cx('separator-dashed')}></div>

                                        <div className="d-flex align-items-center">
                                            <div className={cx('avatar')}>
                                                <span className={cx('weather-icon')}>
                                                    <WiHumidity style={{height: '2rem', width: '2rem'}}/>
                                                </span>
                                            </div>
                                            <div className="flex-1">
                                                <h5> Độ ẩm</h5>
                                            </div>
                                            <div className="d-flex ms-auto align-items-center">
                                                <div className={cx('weather-sun')}>
                                                    <span>88%</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className={cx('separator-dashed')}></div>

                                        <div className="d-flex align-items-center">
                                            <div className={cx('avatar')}>
                                                <span className={cx('weather-icon')}>
                                                    <BsWind style={{height: '2rem', width: '2rem'}}/>
                                                </span>
                                            </div>
                                            <div className="flex-1">
                                                <h5>Gió</h5>
                                            </div>
                                            <div className="d-flex ms-auto align-items-center">
                                                <div className={cx('weather-sun')}>
                                                    <span>4.12 km/giờ</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    )

}


export default Home;