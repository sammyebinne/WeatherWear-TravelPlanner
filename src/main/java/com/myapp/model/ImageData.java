package com.myapp.model;

public class ImageData {

	public static String getImage(String icon) {
		String imgURL = switch (icon) {
		case "01d" -> "/com/myapp/views/images/01d.png";
		case "02d" -> "/com/myapp/views/images/02d.png";
		case "02n" -> "/com/myapp/views/images/02n.png";
		case "03d" -> "/com/myapp/views/images/03d.png";
		case "04d" -> "/com/myapp/views/images/04d.png";
		case "09d" -> "/com/myapp/views/images/09d.png";
		case "10d" -> "/com/myapp/views/images/10d.png";
		case "10n" -> "/com/myapp/views/images/10n.png";
		case "11n" -> "/com/myapp/views/images/11n.png";
		case "13d" -> "/com/myapp/views/images/13d.png";
		case "50d" -> "/com/myapp/views/images/50d.png";
		case "50n" -> "/com/myapp/views/images/50n.png";
		case "humidity" -> "/com/myapp/views/images/humidity.png";
		case "temp" -> "/com/myapp/views/images/temp.png";
		case "wind" -> "/com/myapp/views/images/wind.png";
		default -> "/com/myapp/views/images/01d.png";
		};

		return imgURL;
	}
}
