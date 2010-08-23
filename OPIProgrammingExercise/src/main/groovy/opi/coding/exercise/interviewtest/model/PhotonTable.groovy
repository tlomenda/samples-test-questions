package opi.coding.exercise.interviewtest.model

import java.util.HashMap
import java.util.HashSet;
import java.util.List;
import java.util.Map
import java.util.Set;

/**
 * The PhotonTable contains all possible photon emissions for electron energy level transitions
 * with the range of energy=1 to energy=6.
 * 
 * @author tlomenda
 *
 */
class PhotonTable {
	
	public static final String DIST_ALGORITHM_1 = "algorithm1"
	public static final String DIST_ALGORITHM_2 = "algorithm2"
	public static final String DIST_ALGORITHM_3 = "algorithm3"
		
    Map<Integer, Set<Photon>> photonMap = new HashMap<Integer, Set<Photon>>()
    List<Photon> photonDistributionList = new ArrayList<Photon>()
    
    public PhotonTable(String algorithmToUse) {
        super();
        
        // Initialize the photon table
        addLevel1Transitions()
        addLevel2Transitions()
        addLevel3Transitions()
        addLevel4Transitions()
        addLevel5Transitions()
        
        // Initialize the distribution list photon emissions
		// level 1 - 60%, level2 - 21%, level3 - 12%, level3 - 6%, level5 - 1%
        if (algorithmToUse == DIST_ALGORITHM_1) {
        	initPhotonDistribution1()
        } else if (algorithmToUse == DIST_ALGORITHM_2) {
        	initPhotonDistribution2()
        } else if (algorithmToUse == DIST_ALGORITHM_3) {
        	initPhotonDistribution3()
        } else {
        	initPhotonDistribution1()
        }
    }
    
    def level1Protons() {
        return levelProtons(1)
    }
    def level2Protons() {
    	return levelProtons(2)
    }
    def level3Protons() {
    	return levelProtons(3)
    }
    def level4Protons() {
    	return levelProtons(4)
    }
    def level5Protons() {
    	return levelProtons(5)
    }
    
    def levelProtons(int level) {
    	return photonMap.get(level)
    }
    
    private void addLevel1Transitions() {
        Set<Photon> level1Set = new HashSet()
        
        level1Set.add(new Photon(2,1))
        level1Set.add(new Photon(3,2))
        level1Set.add(new Photon(4,3))
        
        photonMap.put(1, level1Set)
    }
    
    private void addLevel2Transitions() {
        Set<Photon> level2Set = new HashSet();
        
        level2Set.add(new Photon(3,1))
        level2Set.add(new Photon(4,2))
        level2Set.add(new Photon(5,3))
        
        photonMap.put(2, level2Set)
    }
    
    private void addLevel3Transitions() {
        Set<Photon> level3Set = new HashSet();
        
        level3Set.add(new Photon(4,1))
        level3Set.add(new Photon(5,2))
        level3Set.add(new Photon(6,3))
        
        photonMap.put(3, level3Set)
    }
    
    private void addLevel4Transitions() {
        Set<Photon> level4Set = new HashSet();
        
        level4Set.add(new Photon(5,1))
        level4Set.add(new Photon(6,2))
        
        photonMap.put(4, level4Set)
    }
    
    private void addLevel5Transitions() {
        Set<Photon> level5Set = new HashSet();
        
        level5Set.add(new Photon(6,1))
        
        photonMap.put(5, level5Set)
    }
    
    /**
     * Basic probability distribution list
     */
    void initPhotonDistribution1() {
		addToDistribution(level1Protons().toList(), 60)
		addToDistribution(level2Protons().toList(), 21)
		addToDistribution(level3Protons().toList(), 12)
		addToDistribution(level4Protons().toList(), 6)
		addToDistribution(level5Protons().toList(), 1)
	}
    
    /**
     * Add more randomness in the distribution but maintain the same probability
     */
    void initPhotonDistribution2() {
    	int level1Count=0, level2Count=0, level3Count=0, level4Count=0, level5Count=0
    	def level5Index = Math.round(Math.random() * 99)
    	def level1List = level1Protons().toList()
    	def level2List = level2Protons().toList()
    	def level3List = level3Protons().toList()
    	def level4List = level4Protons().toList()
    	def level5List = level5Protons().toList()
    	
    	while(level1Count < 60 && level2Count < 21 && level3Count < 12 && level4Count < 6 && level5Count < 1) {
    		if (level1Count < 60) {
    			photonDistributionList.add(getByRandomIndex(level1List))
    			level1Count++
    		}
    		if (level2Count < 21) {
    			photonDistributionList.add(getByRandomIndex(level2List))
    			level2Count++
    		}
    		if (level3Count < 12) {
    			photonDistributionList.add(getByRandomIndex(level3List))
    			level3Count++
    		}
    		if (level4Count < 6) {
    			photonDistributionList.add(getByRandomIndex(level4List))
    			level4Count++
    		}
    		if (level5Count < 1 && photonDistributionList.size() >= level5Index) {
    			photonDistributionList.add(getByRandomIndex(level5List))
    			level5Count++
    		}
    	}
	}
    
    /**
     * Most ordered distribution
     */
    void initPhotonDistribution3() {
    	int count=0
    	def level1List = level1Protons().toList()
    	def level2List = level2Protons().toList()
    	def level3List = level3Protons().toList()
    	def level4List = level4Protons().toList()
    	def level5List = level5Protons().toList()
    	
    	for (int i=0; i < 60; i++) {
    		if (count == level1List.size()) {
    			count = 0
    		}
    		
    		photonDistributionList.add(level1List[count++])
    	}
    	
    	count = 0
		for (int i=0; i < 21; i++) {
			if (count == level2List.size()) {
    			count = 0
    		}
    		
    		photonDistributionList.add(level2List[count++])		
		}
    	
    	count = 0
		for (int i=0; i < 12; i++) {
			if (count == level3List.size()) {
    			count = 0
    		}
    		
    		photonDistributionList.add(level3List[count++])
		}
    	
    	count = 0
		for (int i=0; i < 6; i++) {
			if (count == level4List.size()) {
    			count = 0
    		}
    		
    		photonDistributionList.add(level4List[count++])
		}
    	
    	count = 0
		for (int i=0; i < 1; i++) {
			if (count == level5List.size()) {
    			count = 0
    		}
    		
    		photonDistributionList.add(level5List[count++])
		}
	}
	
	void addToDistribution(def photons, int numberToAdd) {
		for (int i=0; i < numberToAdd; i++) {
			int index = Math.round(Math.random() * (photons.size()-1))
			photonDistributionList.add(photons[index])
		}
	}
	
	def getByRandomIndex(def photons) {
		int index = Math.round(Math.random() * (photons.size()-1))
		return photons[index]
	}
}
