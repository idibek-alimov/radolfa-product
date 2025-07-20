package tj.alimov.productservice.model;

public class AdvancedSize {
//    // ========== SIZE SYSTEM ENTITY ==========
//    @Entity
//    public class SizeSystem {
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @Column(nullable = false)
//        private String name;
//        private String description;
//
//        @Enumerated(EnumType.STRING)
//        private SizeCategory category;
//
//        @OneToMany(mappedBy = "sizeSystem", cascade = CascadeType.ALL, orphanRemoval = true)
//        @OrderBy("sortOrder ASC")
//        private List<SizeOption> options = new ArrayList<>();
//
//        @ManyToMany
//        @JoinTable(name = "size_system_regions",
//                joinColumns = @JoinColumn(name = "size_system_id"),
//                inverseJoinColumns = @JoinColumn(name = "region_id"))
//        private Set<Region> applicableRegions = new HashSet<>();
//
//        public void addOption(SizeOption option) {
//            options.add(option);
//            option.setSizeSystem(this);
//        }
//    }
//
//    public enum SizeCategory {
//        CLOTHING, SHOES, ACCESSORIES, UNDERWEAR, SPORTS, UNISEX
//    }
//
//    // ========== SIZE OPTION ENTITY ==========
//    @Entity
//    public class SizeOption {
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @Column(nullable = false)
//        private String code;
//        private String displayValue;
//        private String description;
//
//        @Enumerated(EnumType.STRING)
//        private SizeType type;
//        private Integer sortOrder;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "size_system_id")
//        private SizeSystem sizeSystem;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        private SizeGroup group;
//
//        @OneToMany(mappedBy = "sizeOption", cascade = CascadeType.ALL)
//        private List<SizeConversion> conversions = new ArrayList<>();
//
//        @ManyToMany(mappedBy = "availableSizes")
//        private Set<Product> products = new HashSet<>();
//    }
//
//    public enum SizeType {
//        NUMERIC, ALPHABETIC, DESCRIPTIVE, DIMENSIONAL, RANGE
//    }
//
//    // ========== SIZE GROUP ENTITY ==========
//    @Entity
//    public class SizeGroup {
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private String name;
//        private String description;
//
//        @OneToMany(mappedBy = "group")
//        private List<SizeOption> sizeOptions = new ArrayList<>();
//
//        @ManyToOne
//        private SizeSystem sizeSystem;
//    }
//
//    // ========== SIZE CONVERSION ENTITY ==========
//    @Entity
//    public class SizeConversion {
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "size_option_id")
//        private SizeOption sizeOption;
//
//        @Enumerated(EnumType.STRING)
//        private Region targetRegion;
//        private String equivalentSize;
//    }
//
//    public enum Region {
//        US, EU, UK, ASIA, JP, AU
//    }
//
//    // ========== SIZE SERVICE ==========
//    @Service
//    @RequiredArgsConstructor
//    public class SizeService {
//        private final SizeSystemRepository sizeSystemRepository;
//        private final SizeOptionRepository sizeOptionRepository;
//
//        public List<SizeSystem> getSizeSystemsForCategory(SizeCategory category) {
//            return sizeSystemRepository.findByCategory(category);
//        }
//
//        @Transactional
//        public SizeSystem createSizeSystemWithOptions(SizeSystemCreateDTO dto) {
//            SizeSystem system = new SizeSystem();
//            system.setName(dto.getName());
//            system.setCategory(dto.getCategory());
//
//            int order = 1;
//            for (SizeOptionCreateDTO optionDto : dto.getOptions()) {
//                SizeOption option = new SizeOption();
//                option.setCode(optionDto.getCode());
//                option.setDisplayValue(optionDto.getDisplayValue());
//                option.setType(optionDto.getType());
//                option.setSortOrder(order++);
//                system.addOption(option);
//            }
//            return sizeSystemRepository.save(system);
//        }
//
//        public SizeSelectionDTO getSizesForProductType(Long productTypeId, Region region) {
//            // Implementation as shown previously
//        }
//
//        @Transactional
//        public void addSizeConversions(Long sizeOptionId, List<SizeConversionDTO> conversions) {
//            // Implementation as shown previously
//        }
//    }
//
//    // ========== DTO CLASSES ==========
//    @Data
//    public class SizeSystemCreateDTO {
//        private String name;
//        private SizeCategory category;
//        private List<SizeOptionCreateDTO> options;
//    }
//
//    @Data
//    public class SizeOptionCreateDTO {
//        private String code;
//        private String displayValue;
//        private SizeType type;
//    }
//
//    @Data
//    public class SizeConversionDTO {
//        private Region targetRegion;
//        private String equivalentSize;
//    }
//
//    @Data
//    public class SizeSelectionDTO {
//        private Long systemId;
//        private String systemName;
//        private List<SizeOptionDTO> options;
//    }
//
//    @Data
//    public class SizeOptionDTO {
//        private Long id;
//        private String code;
//        private String displayValue;
//        private boolean isInStock;
//    }
}
