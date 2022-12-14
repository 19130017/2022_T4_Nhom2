import styles from './DefaultLayout.module.scss';
import classNames from "classnames/bind";
import Header from "~/layouts/components/Header";
import Footer from "~/layouts/components/Footer";

const cx = classNames.bind(styles);

function DefaultLayout({children}) {
    return <div className={cx('default-layout')}>
        <Header/>

        <div className={cx('app-content')}>{children}</div>
        <Footer/>
    </div>;
}

export default DefaultLayout;