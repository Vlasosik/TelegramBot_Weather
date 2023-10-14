package org.example.telegram_bot_weather.weatherAPI;


import lombok.Getter;

@Getter
public enum WeatherCity {
    KYIV("KYIV"),
    CHERNIGIV("CHERNIGIV"),
    SUMY("SUMY"),
    CHERKASY("CHERKASY"),
    ZHYTOMYR("ZHYTOMYR"),
    // Захід
    UZHGOROD("UZHGOROD"),
    LVIV("LVIV"),
    IVANO_FRANKIVSK("IVANO_FRANKIVSK"),
    TERNOPIL("TERNOPIL"),
    LUTSK("LUTSK"),
    // Південь
    ODESA("ODESA"),
    MYKOLAIV("MYKOLAIV"),
    KHARKIV("KHARKIV"),
    POLTAVA("POLTAVA"),
    DNIPRO("DNIPRO"),
    // Схід
    ZAPORIZHZHIA("ZAPORIZHZHIA"),
    DONETSK("DONETSK"),
    LUHANSK("LUHANSK"),
    MARIUPOL("MARIUPOL"),
    SIMFEROPOL("SIMFEROPOL");

    private final String cityName;
    WeatherCity(String cityName) {
        this.cityName = cityName;
    }

}
