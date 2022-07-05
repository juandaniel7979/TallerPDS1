package co.com.poli.tallerpds.mapper;

import java.text.ParseException;

public interface IMapper <I,O>{
    public O map(I in) throws ParseException;
}
