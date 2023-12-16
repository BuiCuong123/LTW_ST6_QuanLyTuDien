// File: script.js

function formatDateTime(date) {
    const day = date.getDate();
    const month = date.getMonth() + 1; // Lưu ý: Tháng bắt đầu từ 0
    const year = date.getFullYear();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    
    // Định dạng số để đảm bảo hiển thị dưới dạng 2 chữ số
    const formattedDay = day.toString().padStart(2, '0');
    const formattedMonth = month.toString().padStart(2, '0');
    const formattedHours = hours.toString().padStart(2, '0');
    const formattedMinutes = minutes.toString().padStart(2, '0');
    const formattedSeconds = seconds.toString().padStart(2, '0');

    return `${formattedDay}/${formattedMonth}/${year} ${formattedHours}:${formattedMinutes}:${formattedSeconds}`;
}

function updateCurrentDateTime() {
    const currentDateTimeElement = document.getElementById('currentDateTime');
    if (currentDateTimeElement) {
        const currentDate = new Date();
        const formattedDateTime = formatDateTime(currentDate);
        currentDateTimeElement.textContent = formattedDateTime;
    }
}

setInterval(updateCurrentDateTime, 1000);
updateCurrentDateTime();