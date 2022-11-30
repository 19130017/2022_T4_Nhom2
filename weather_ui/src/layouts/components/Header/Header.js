import styles from './Header.module.scss';
import classNames from "classnames/bind";
import {InputAdornment, TextField, Button} from '@mui/material';
import SearchIcon from '@mui/icons-material/SearchOutlined';
import {Link} from 'react-router-dom';

const cx = classNames.bind(styles);

function Header() {
    return <header id={cx('header')}>
        <div className={cx('container')}>
            <div className={cx('content')}>
                <div className={cx('header-image')}>
                    <a href='/'>
                        <img src='https://thoitiet.vn/img/logo-header.png'
                             alt='thoitiet.vn'
                             className={cx('logo')}/>
                    </a>
                </div>
                <div className={cx('header-search')}>
                    <TextField
                        id="outlined-basic"
                        variant="outlined"
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <SearchIcon sx={{width: "2.4rem", height: "2.4rem"}}/>
                                </InputAdornment>
                            ),
                        }}
                        placeholder="Nhập tên địa điểm"
                        sx={{width: "300px", marginLeft: "10px", fontSize: "2rem"}}
                    />
                </div>

                <Button variant="outlined" component={Link} to={"/report"} className="ms-auto">Xem báo cáo</Button>
            </div>
        </div>
    </header>
}

export default Header;