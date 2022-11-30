import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    TablePagination,
    Box,
    TableFooter,
    IconButton
} from '@mui/material';
import {useTheme} from '@mui/material/styles';
import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';


import {useEffect, useState} from "react";
import * as weatherService from "~/services/weatherService"
import {CSVLink} from "react-csv";
import PropTypes from 'prop-types';
import style from "./Report.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(style);


function TablePaginationActions(props) {
    const theme = useTheme();
    const {count, page, rowsPerPage, onPageChange} = props;

    const handleFirstPageButtonClick = (event) => {
        onPageChange(event, 0);
    };

    const handleBackButtonClick = (event) => {
        onPageChange(event, page - 1);
    };

    const handleNextButtonClick = (event) => {
        onPageChange(event, page + 1);
    };

    const handleLastPageButtonClick = (event) => {
        onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
    };

    return (
        <Box sx={{flexShrink: 0, ml: 2.5}}>
            <IconButton
                onClick={handleFirstPageButtonClick}
                disabled={page === 0}
                aria-label="first page"
            >
                {theme.direction === 'rtl' ? <LastPageIcon/> : <FirstPageIcon/>}
            </IconButton>
            <IconButton
                onClick={handleBackButtonClick}
                disabled={page === 0}
                aria-label="previous page"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowRight/> : <KeyboardArrowLeft/>}
            </IconButton>
            <IconButton
                onClick={handleNextButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="next page"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowLeft/> : <KeyboardArrowRight/>}
            </IconButton>
            <IconButton
                onClick={handleLastPageButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="last page"
            >
                {theme.direction === 'rtl' ? <FirstPageIcon/> : <LastPageIcon/>}
            </IconButton>
        </Box>
    );
}

TablePaginationActions.propTypes = {
    count: PropTypes.number.isRequired,
    onPageChange: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
};

function Report() {
    const [weathers, setWeathers] = useState([]);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    const emptyRows =
        page > 0 ? Math.max(0, (1 + page) * rowsPerPage - weathers.length) : 0;

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    useEffect(() => {
        const fetchApi = async () => {
            const result = await weatherService.getAll();
            setWeathers(result)
        }
        fetchApi();
    }, [])

    return (
        <div className="container">
            <CSVLink
                data={weathers}
                filename={"weather-current.csv"}
                className="btn btn-primary mb-2">
                Download csv
            </CSVLink>

            <TableContainer component={Paper}>
                <Table sx={{minWidth: 500}} aria-label="custom pagination table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Natural Key</TableCell>
                            <TableCell align="right">Ngày dự báo</TableCell>
                            <TableCell align="right">Tỉnh/Thành phố</TableCell>
                            <TableCell align="right">Description</TableCell>
                            <TableCell align="right">Nhiệt độ thấp</TableCell>
                            <TableCell align="right">Nhiệt độ cao</TableCell>
                            <TableCell align="right">Độ ẩm</TableCell>
                            <TableCell align="right">Tốc độ gió</TableCell>
                            <TableCell align="right">Trạng thái</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {(rowsPerPage > 0
                                ? weathers.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                : weathers
                        ).map((row, index) => (
                            <TableRow key={index}>
                                <TableCell scope="row">
                                    {row.natural_key}
                                </TableCell>
                                <TableCell align="right">
                                    {row.forecast_date.date}
                                </TableCell>
                                <TableCell align="right">
                                    {row.province.name}
                                </TableCell>
                                <TableCell align="right">
                                    {row.description}
                                </TableCell>
                                <TableCell align="right">
                                    {row.min_temp}°
                                </TableCell>
                                <TableCell align="right">
                                    {row.max_temp}°
                                </TableCell>
                                <TableCell align="right">
                                    {row.humidity}%
                                </TableCell>
                                <TableCell align="right">
                                    {row.wind_speed} km/giờ
                                </TableCell>
                                <TableCell align="right" className={cx((row.expired_date === 1462) ? "green" : "red")}>
                                    {(row.expired_date === 1462) ? "Hoạt động" : "Hết hạn"}
                                </TableCell>
                            </TableRow>
                        ))}

                        {emptyRows > 0 && (
                            <TableRow style={{height: 53 * emptyRows}}>
                                <TableCell colSpan={6}/>
                            </TableRow>
                        )}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TablePagination
                                rowsPerPageOptions={[5, 10, 25, {label: 'All', value: -1}]}
                                colSpan={3}
                                count={weathers.length}
                                rowsPerPage={rowsPerPage}
                                page={page}
                                SelectProps={{
                                    inputProps: {
                                        'aria-label': 'rows per page',
                                    },
                                    native: true,
                                }}
                                onPageChange={handleChangePage}
                                onRowsPerPageChange={handleChangeRowsPerPage}
                                ActionsComponent={TablePaginationActions}
                            />
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
        </div>
    )


}

export default Report;