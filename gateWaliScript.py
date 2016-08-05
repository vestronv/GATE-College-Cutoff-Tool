import sys, os, urllib, urllib2, re, httplib
from bs4 import BeautifulSoup as bs
from argparse import ArgumentParser

base_url = "http://cutoffs.aglasem.com/143327"
base_dir = os.getcwd()+"/gate_data"

opener = urllib2.build_opener()
#Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11
opener.addheaders = [('User-Agent','Mozilla/5.0')]
	
def download_save(cur_dir,colg_name_,img_link,year_dir) :
	print '\t[+]saving images of '+colg_name_
	cur_temp_dir = cur_dir+colg_name_+'/'
	os.chdir(cur_temp_dir)
	if os.listdir(cur_temp_dir) == [] :
		pp=1
		for il in img_link :
			#download image let 'abcd.jpg' and save as -> 20XX_{1,2,3 - denoting round}_colgName_.png
			file_name_ = year_dir+'_'+`pp`+'_'+colg_name_+'_'+'.jpg'
			pp+=1
			urllib.urlretrieve(il, file_name_)
	print '   Done college '+colg_name_+'\n'
		

def find_year(link) :
	#opener = urllib2.build_opener()
	#Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11
	#opener.addheaders = [('User-Agent','Mozilla/5.0')]
	raw_html = opener.open(link).read()
	raw_html = str(raw_html)
	the_string = re.compile(r'CCMT Cut Off [\d]{4}').findall(raw_html)[0]
	year = (re.compile(r'[\d]{4}').findall(the_string)[0])
	return year
	
def do_stuff() :
	print 'Connecting . . .'
	raw_html = opener.open(base_url).read()
	raw_soup = bs(raw_html)
	div_tag = raw_soup.find('div',{'class':'entry-content'})
	colg_links = []
	colg_name = []
	print 'Saving college name and links...'
	for li in div_tag.find_all('li') :
		if 'CCMT' in li.get_text() :
			colg_links.append(li.find('a')['href'])
			colg_name.append(re.compile(r'CCMT Cut Off for\s+(.*)$').findall(li.get_text()))
			#print li.find('a')['href']
		#break
	
	year_dir = find_year(colg_links[0])
	if not os.path.exists(base_dir) :
		os.makedirs(base_dir)
	if not os.path.exists(base_dir+'/'+year_dir) :
		os.makedirs(base_dir+'/'+year_dir)
	cur_dir = base_dir+'/'+year_dir+'/'
	print 'Interating over all colleges...'
	for colg_name_,link in zip(colg_name,colg_links) :
		colg_name_[0] = colg_name_[0].encode('ascii','ignore')
		print 'Iterating : '+colg_name_[0]+' -> '+link
		colg_html = opener.open(link).read()
		raw_soup1 = bs(colg_html)
		div_tag1 = raw_soup1.find('div',{'class':'entry-content'})
		div_tag1 = str(div_tag1)
		img_link = []
		i=0
		breaker = 0
		for ch in div_tag1 :
			if breaker == 3 :
				break 
			if div_tag1[i]=='i' :
				if div_tag1[i+1]=='m' :
					if div_tag1[i+2]=='g' :
						if div_tag1[i+3]==' ' :
							if div_tag1[i+15]=='"' :
								j=i+16
								img = ''
								while div_tag1[j] != '"' :
									img += str(div_tag1[j])
									j+=1
								i+=30
								breaker += 1
								img_link.append(img)
			i+=1
		if not os.path.exists(cur_dir+colg_name_[0]) :
			os.makedirs(cur_dir+colg_name_[0])
			download_save(cur_dir,colg_name_[0],img_link,year_dir)
		else :
			download_save(cur_dir,colg_name_[0],img_link,year_dir)
		os.chdir(cur_dir)
	os.chdir(base_dir)	
	
if __name__=='__main__' :
	parser = ArgumentParser()
	parser.add_argument("u",
		help="add ' base_url' ",
		type=str
		)
	
	args = parser.parse_args()
	#if len(args) != 1 :
	#	parser.error("Wrong format")
	#else :
	base_url = args.u
	do_stuff()
