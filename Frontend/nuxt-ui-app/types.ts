export interface AdSpace {
	id: number;
	url: string;
	status: 'active' | 'inactive';
	keywords: string[];
	size: { width: number; height: number };
	position: string;
	ad_types: string[];
	min_cpm: number;
	floor_price: number;
	allowed_advertisers: string[];
	blocked_advertisers: string[];
	allowed_categories: string[];
	blocked_categories: string[];
	viewability: number;
	device_types: string[];
	geo_targeting: string[];
	language: string;
	secure: boolean;
	earnings: number;
}
