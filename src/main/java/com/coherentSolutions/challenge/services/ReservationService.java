package com.coherentSolutions.challenge.services;

import com.coherentSolutions.challenge.dtos.PageInfo;
import com.coherentSolutions.challenge.dtos.PaginatedReservations;
import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.exceptions.DataNotFoundException;
import com.coherentSolutions.challenge.exceptions.GeneralException;
import com.coherentSolutions.challenge.exceptions.IncorrectDataBadRequestException;
import com.coherentSolutions.challenge.models.Reservation;
import com.coherentSolutions.challenge.models.ReservationDate;
import com.coherentSolutions.challenge.repositories.ReservationDAO;
import com.coherentSolutions.challenge.repositories.ReservationDateDAO;
import com.coherentSolutions.challenge.services.contracts.ReservationServiceContract;
import com.coherentSolutions.challenge.util.PageableHelper;
import com.coherentSolutions.challenge.util.converters.ReservationConverter;
import com.coherentSolutions.challenge.util.enums.AppMessages;
import com.coherentSolutions.challenge.util.formatters.ReservationFormatter;
import com.coherentSolutions.challenge.util.validators.ReservationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class ReservationService implements ReservationServiceContract {

    @Autowired
    private PageableHelper pageableHelper;

    @Autowired
    private ReservationConverter converter;

    @Autowired
    private ReservationValidator dataValidator;

    @Autowired
    private ReservationFormatter dataFormatter;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private ReservationDateDAO reservationDateDAO;


    @Override
    public Optional<PaginatedReservations> findAllReservations(Integer pageNumber, Integer pageSize) {
        try{
            Long totalNumberOfRecords = reservationDAO.count();
            if(totalNumberOfRecords == 0)
                return Optional.empty();
            return fetchAllReservationsData(pageNumber, pageSize, totalNumberOfRecords);
        }catch (DataNotFoundException | IncorrectDataBadRequestException exception){
            log.info(AppMessages.INFO_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw exception;
        }catch (Exception exception){
            log.error(AppMessages.ERROR_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw new GeneralException(exception.getMessage(), exception);
        }
    }

    public Optional<PaginatedReservations> fetchAllReservationsData(Integer pageNumber, Integer pageSize, Long totalNumberOfRecords) {
        try{
            PageInfo pageInfo = pageableHelper.helper(pageNumber, pageSize, totalNumberOfRecords);
            PaginatedReservations paginatedReservations = new PaginatedReservations();
            paginatedReservations.setPageInfo(pageInfo);
            Pageable page = PageRequest.of(pageInfo.getCurrentPage() -1, pageInfo.getPageSize());
            List<Reservation> reservations = reservationDAO.findAll(page).toList();
            paginatedReservations.setReservations(converter.fromEntity(reservations));
            return Optional.of(paginatedReservations);
        }catch (DataNotFoundException | IncorrectDataBadRequestException exception){
            log.info(AppMessages.INFO_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw exception;
        }catch (Exception exception){
            log.error(AppMessages.ERROR_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw new GeneralException(exception.getMessage(), exception);
        }
    }

    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale( Locale.US );

            dataValidator.validateReservationToCreateANewRecord(reservationRequest);
            Reservation formattedReservation = dataFormatter.formatReservationBeforePersistToCreateANewRecord(reservationRequest);
            List<ReservationDate> reservationDateList = new ArrayList<>();
            for(int i = 0; i<reservationRequest.getReservationDates().size(); i++){
                ReservationDate reservationDate = ReservationDate.builder()
                        .reservationDate(LocalDate.parse(reservationRequest.getReservationDates().get(i), formatter))
                        .build();
                reservationDateList.add(reservationDate);
            }
            reservationDateList.forEach(reservationDate -> reservationDate.setReservation(formattedReservation));
            if(formattedReservation.getId() == null) {
                formattedReservation.setReservationDates(reservationDateList);
                return converter.fromEntity(reservationDAO.save(formattedReservation));
            } else
                return null;
        }catch (DataNotFoundException | IncorrectDataBadRequestException exception){
            log.info(AppMessages.INFO_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw exception;
        }catch (Exception exception){
            log.error(AppMessages.ERROR_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw new GeneralException(exception.getMessage(), exception);
        }
    }

    @Override
    @Transactional
    public Optional<ReservationResponse> updateReservation(ReservationRequest reservationRequest) {
        try{
            dataValidator.validateReservationToUpdateARecord(reservationRequest);
            Optional<Reservation> optionalReservation = Optional.of(reservationDAO.findById(Integer.valueOf(reservationRequest.getId())))
                    .orElseThrow(() -> new DataNotFoundException(AppMessages.RESERVATION_DOES_NOT_EXIST.getMessage()));
            if(optionalReservation.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale( Locale.US );
                Reservation reservationToUpdate = optionalReservation.get();

                Reservation formattedReservation = dataFormatter.formatReservationBeforePersistToUpdateARecord(reservationRequest);
                updateReservationValues(reservationRequest, reservationToUpdate, formattedReservation);

                List<ReservationDate> reservationDateList = new ArrayList<>();
                if(reservationRequest.getReservationDates() != null){
                    reservationDateDAO.deleteAllByReservationId(Integer.valueOf(reservationRequest.getId()));
                    for(int i = 0; i<reservationRequest.getReservationDates().size(); i++){
                        ReservationDate reservationDate = ReservationDate.builder()
                                .reservationDate(LocalDate.parse(reservationRequest.getReservationDates().get(i), formatter))
                                .reservation(reservationToUpdate)
                                .build();
                        reservationDateList.add(reservationDate);
                    }
                    reservationDateList.forEach(reservationDate -> reservationDate.setReservation(reservationToUpdate));
                }
                reservationToUpdate.setReservationDates(reservationDateList);
                return Optional.ofNullable(converter.fromEntity(reservationDAO.save(reservationToUpdate)));
            } else
                return Optional.empty();

        }catch (DataNotFoundException | IncorrectDataBadRequestException exception){
            log.info(AppMessages.INFO_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw exception;
        }catch (Exception exception){
            log.error(AppMessages.ERROR_MESSAGE.getMessage() + exception.getMessage(), exception);
            throw new GeneralException(exception.getMessage(), exception);
        }
    }

    public void updateReservationValues(ReservationRequest reservationRequest, Reservation reservationToUpdate, Reservation formattedReservation){
        if(reservationRequest.getClientFullName() != null)
            reservationToUpdate.setClientFullName(formattedReservation.getClientFullName());
        if(reservationRequest.getRoomNumber() != null)
            reservationToUpdate.setRoomNumber(formattedReservation.getRoomNumber());
        if(reservationRequest.getRoomNumber() != null)
            reservationToUpdate.setRoomNumber(formattedReservation.getRoomNumber());
    }
}
