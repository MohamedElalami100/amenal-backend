package com.amenal.amenalbackend.budget.core.port.in;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.port.out.TacheTableDao;
import com.amenal.amenalbackend.budget.infrastructure.dto.ChartDataDto;
import com.amenal.amenalbackend.budget.infrastructure.dto.TacheTableDto;

public class GetChartDataUseCase {
	private TacheTableDao tacheTableDao;
	
	public GetChartDataUseCase(TacheTableDao tacheTableDao) {
		super();
		this.tacheTableDao = tacheTableDao;
	}

	public ChartDataDto getChartDataByProjectId(Integer id, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByProjectIdAndCharge(id, charge);
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByProjectIdAndTacheIds(Integer id, Integer[] tacheIds, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByProjectIdAndCharge(id, charge);
			// filter taches with ids in tacheIds
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(tacheIds, tache.getId()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByProjectIdAndLots(Integer id, String[] lots, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByProjectIdAndCharge(id, charge);
			// filter taches with ids in lotIds
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(lots, tache.getLot()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByProjectIdAndProduits(Integer id, String[] produits, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByProjectIdAndCharge(id, charge);
			// filter taches with ids in produits
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(produits, tache.getProduit()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByAvenantId(Integer id, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByAvenantIdAndCharge(id, charge);
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByAvenantIdAndTacheIds(Integer id, Integer[] tacheIds, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByAvenantIdAndCharge(id, charge);
			// filter taches with ids in tacheIds
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(tacheIds, tache.getId()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByAvenantIdAndLots(Integer id, String[] lots, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByAvenantIdAndCharge(id, charge);
			// filter taches with ids in lotIds
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(lots, tache.getLot()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}
	
	public ChartDataDto getChartDataByAvenantIdAndProduits(Integer id, String[] produits, String charge) {
		try {
			List<TacheTableDto> tacheTable = tacheTableDao.getTacheTableByAvenantIdAndCharge(id, charge);
			// filter taches with ids in produits
			tacheTable = tacheTable.stream().filter(tache -> Arrays.binarySearch(produits, tache.getProduit()) >= 0).collect(Collectors.toList());
			return getChartData(tacheTable);
		} catch (Exception e) {
			return new ChartDataDto(0);
		}
	}

	private ChartDataDto getChartData(List<TacheTableDto> tacheTable) {
		// Remove taches with null attributes
		tacheTable = tacheTable.stream().filter(tache -> tache.getDdb() != null && tache.getDfb() != null
				&& tache.getDlb() != null && tache.getDlb() != 0).collect(Collectors.toList());

		if (tacheTable.isEmpty()) {
			return new ChartDataDto(0);
		}

		Optional<TacheTableDto> minDateObject = tacheTable.stream().min((o1, o2) -> o1.getDdb().compareTo(o2.getDdb()));
		Optional<TacheTableDto> maxDateObject = tacheTable.stream().max((o1, o2) -> o1.getDfb().compareTo(o2.getDfb()));

		LocalDate minDate;
		LocalDate maxDate;
		Integer delai = 0;
		if (minDateObject.isPresent() && maxDateObject.isPresent()) {
			minDate = minDateObject.get().getDdb();
			maxDate = maxDateObject.get().getDfb();
			delai = (int) (maxDate.toEpochDay() - minDate.toEpochDay() + 1);
		} else {
			return new ChartDataDto(0);
		}

		if (delai <= 0) {
			return new ChartDataDto(0);
		}

		ChartDataDto chartData = new ChartDataDto(delai);

		for (Integer i = 0; i < delai; i++) {
			LocalDate curDate = minDate.plusDays(i);
			chartData.getLabels()[i] = curDate;

			List<TacheTableDto> tachesInCurDate = tacheTable.stream().filter(tache -> tache.getDdb() == curDate)
					.collect(Collectors.toList());

			for (TacheTableDto tache : tachesInCurDate) {
				// qte et mt journalieres:
				chartData.getQteJourChargePonctuelle()[i] += tache.getQpb();
				chartData.getQteJourProduitPonctuelle()[i] += tache.getQpm();
				chartData.getMtJourChargePonctuelle()[i] += tache.getMpb();
				chartData.getMtJourProduitPonctuelle()[i] += tache.getMpm();

				// qte et mt lisse:
				Integer curDelai = tache.getDlb();

				for (Integer j = 0; j < curDelai; j++) {
					if (i + j >= delai)
						break;
					chartData.getQteJourChargeLisse()[i + j] += tache.getQpb() / curDelai;
					chartData.getQteJourProduitLisse()[i + j] += tache.getQpm() / curDelai;
					chartData.getMtJourChargeLisse()[i + j] += tache.getMpb() / curDelai;
					chartData.getMtJourProduitLisse()[i + j] += tache.getMpm() / curDelai;
				}
			}	
		}
		
		//qte et mt cumule:
		chartData.getQteCumChargePonctuelle()[0] = chartData.getQteJourChargePonctuelle()[0];
		chartData.getQteCumProduitPonctuelle()[0] = chartData.getQteJourProduitPonctuelle()[0];
		chartData.getMtCumChargePonctuelle()[0] = chartData.getMtJourChargePonctuelle()[0];
		chartData.getMtCumProduitPonctuelle()[0] = chartData.getMtJourProduitPonctuelle()[0];
		chartData.getQteCumChargeLisse()[0] = chartData.getQteJourChargeLisse()[0];
		chartData.getQteCumProduitLisse()[0] = chartData.getQteJourProduitLisse()[0];
		chartData.getMtCumChargeLisse()[0] = chartData.getMtJourChargeLisse()[0];
		chartData.getMtCumProduitLisse()[0] = chartData.getMtJourProduitLisse()[0];

		for (Integer i = 1; i < delai; i++) {
			chartData.getQteCumChargePonctuelle()[i] = chartData.getQteCumChargePonctuelle()[i-1] + chartData.getQteJourChargePonctuelle()[i];
			chartData.getQteCumProduitPonctuelle()[i] = chartData.getQteCumProduitPonctuelle()[i-1] + chartData.getQteJourProduitPonctuelle()[i];;
			chartData.getMtCumChargePonctuelle()[i] = chartData.getMtCumChargePonctuelle()[i-1] + chartData.getMtJourChargePonctuelle()[i];;
			chartData.getMtCumProduitPonctuelle()[i] = chartData.getMtCumProduitPonctuelle()[i-1] + chartData.getMtJourProduitPonctuelle()[i];;
			chartData.getQteCumChargeLisse()[i] = chartData.getQteCumChargeLisse()[i-1] + chartData.getQteJourChargeLisse()[i];;
			chartData.getQteCumProduitLisse()[i] = chartData.getQteCumProduitLisse()[i-1] + chartData.getQteJourProduitLisse()[i];;
			chartData.getMtCumChargeLisse()[i] = chartData.getMtCumChargeLisse()[i-1] + chartData.getMtJourChargeLisse()[i];;
			chartData.getMtCumProduitLisse()[i] = chartData.getMtCumProduitLisse()[i-1] + chartData.getMtJourProduitLisse()[i];;			
		}

		return chartData;

	}

}
