import style from './CardWeather.module.scss';
import classNames from "classnames/bind";
import OpacityIcon from '@mui/icons-material/Opacity';

const cx = classNames.bind(style);

function CardWeather({data}) {
    return (
        <div className={cx("location-weather")}>
            <div className={cx('card')}>
                <a href="/ha-giang">
                    <h3 className={cx('card-city-title')}>{data.province.name}</h3>
                    <div className={cx('card-city-body')}>
                        <img src="https://data.thoitiet.vn/weather/icons/02d@2x.png" alt="description"/>
                        <div className={cx('precipitation')}>
                            <OpacityIcon/>
                            {data.humidity}%
                        </div>
                    </div>
                    <p className={cx('description')}>{data.description}</p>
                    <div className={cx('card-city-footer')}>
                        <p title="Hiện tại">{data.min_temp}°</p>
                        <p>/</p>
                        <p title="Cảm giác như">{data.max_temp}°</p>
                    </div>
                </a>
            </div>
        </div>
    )
}

export default CardWeather;