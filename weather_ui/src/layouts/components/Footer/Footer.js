import styles from './Footer.module.scss';
import classNames from "classnames/bind";
import {Link} from 'react-router-dom';
import EmailIcon from '@mui/icons-material/EmailOutlined';
import FacebookIcon from '@mui/icons-material/Facebook';
import InstagramIcon from '@mui/icons-material/Instagram';
import TwitterIcon from '@mui/icons-material/Twitter';
import YouTubeIcon from '@mui/icons-material/YouTube';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import PinterestIcon from '@mui/icons-material/Pinterest';

const cx = classNames.bind(styles);

function Footer() {
    return <footer id={cx("footer")}>
        <div className={cx("container")}>
            <div className={cx("content")}>
                <div className={cx('copyright')}>
                    <div className={cx('footer-item')}>
                        <span>© 2021 Kênh thông tin dự báo
                            <Link to={"/"}>Thời tiết</Link>
                        </span>
                        <a href='/'>
                            <EmailIcon/>
                            info@thoitiet.vn
                        </a>

                        <div className={cx("social-items")}>
                            <a href="/" className={cx('social-footer', 'social-facebook')}> <FacebookIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                            <a href="/" className={cx('social-footer', 'social-instagram')}> <InstagramIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                            <a href="/" className={cx('social-footer', 'social-twitter')}> <TwitterIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                            <a href="/" className={cx('social-footer', 'social-youtube')}> <YouTubeIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                            <a href="/" className={cx('social-footer', 'social-linkedin')}> <LinkedInIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                            <a href="/" className={cx('social-footer', 'social-pinterest')}> <PinterestIcon
                                sx={{width: '2.4rem', height: '2.4rem'}}/> </a>
                        </div>
                    </div>
                </div>
                <div className={cx("policy")}>
                    <ul className={cx('footer-item', 'list-inline')}>
                        <li className={cx('list-inline-item')}>
                            <a href="/">Widget</a>
                        </li>
                        <li className={cx('list-inline-item')}>
                            <a href="/">Điều khoản dịch vụ</a>
                        </li>
                        <li className={cx('list-inline-item')}>
                            <a href="/">Chính sách bảo mật</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
}

export default Footer;