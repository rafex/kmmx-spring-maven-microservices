/* jshint asi: true, globalstrict: true */

'use strict'

import autoprefixer from 'autoprefixer';
import babel from 'gulp-babel'; // si no uso typescrypt en el código no se necesita esto
import sourcemaps from 'gulp-sourcemaps';
import browsersync from 'browser-sync';
import cssnano from 'cssnano';
import concat from 'gulp-concat';
import cleanCSS from 'gulp-clean-css';
import del from 'del';
import eslint from 'gulp-eslint';
import gulp from 'gulp';
import imagemin from 'gulp-imagemin';
import plumber from 'gulp-plumber';
import postcss from 'gulp-postcss';
import pug from 'gulp-pug';
import rename from 'gulp-rename';
import sass from 'gulp-sass';
import uglify from 'gulp-uglify';
import htmlmin from 'gulp-htmlmin';


const paths = {
  assets: {
    dist: './dist/assets',
    src: './src/assets/**/*.*'
  },
  dist: './dist',
  pug: {
    exclude: '!./src/views/**/{includes}/**/*.pug',
    src: './src/views/**/*.pug'
  },
  scripts: './src/scripts/*.js',
  stylus: {
    exclude: '!./src/**/{components,libs,mixins,partials,theme}/**/*.scss',
    src: './src/**/*.scss'
  },
  external_scripts: [
    'node_modules/jquery/dist/jquery.js',
    'node_modules/jquery-validation/dist/jquery.validate.js',
    'node_modules/datatables.net/js/jquery.dataTables.js',
    'node_modules/datatables.net-dt/js/dataTables.dataTables.js',
    'node_modules/bootstrap/js/src'
  ]
};

// BrowserSync
function browserSync(done) {
  browsersync.init({
    server: {
      baseDir: "./dist"
    },
    open: false,
    watch: true,
    port: 3000
  });
  done();
}

// BrowserSync Reload
function browserSyncReload(done) {
  browsersync.reload();
  done();
}

// Clean
const clean = () => del(['dist']);

function viewsTask() {
  return gulp.src([paths.pug.src, paths.pug.exclude])
    .pipe(pug())
    .pipe(htmlmin())
    .pipe(gulp.dest(paths.dist));
}

function stylesTask() {
  return gulp.src([paths.stylus.src, paths.stylus.exclude])
    .pipe(sass({
      compress: true
    }).on('error', sass.logError))
    .pipe(cleanCSS())
    // pass in options to the stream
    .pipe(rename({
      basename: 'main',
      suffix: '.min'
    }))
    .pipe(postcss([autoprefixer(), cssnano()]))
    .pipe(gulp.dest(paths.dist))
    .pipe(browsersync.stream());
}

function scriptsLint() {
  return gulp
    .src([paths.scripts, './gulpfile.babel.js'])
    .pipe(plumber())
    .pipe(eslint())
    .pipe(eslint.format())
    .pipe(eslint.failAfterError());
}

function scriptsTask() {
  return gulp.src(paths.scripts)
    .pipe(sourcemaps.init())
    .pipe(babel({
      presets: ['@babel/env']
    }))
    .pipe(uglify())
    .pipe(concat('main.min.js'))
    .pipe(sourcemaps.write('maps/'))
    .pipe(gulp.dest(paths.dist))
    .pipe(browsersync.stream());
}

function assetsTask() {
  return gulp.src(paths.assets.src, {
      since: gulp.lastRun(assetsTask)
    })
    .pipe(
      imagemin([
        imagemin.gifsicle({
          interlaced: true
        }),
        imagemin.svgo({
          plugins: [{
            removeViewBox: false,
            collapseGroups: true
          }]
        })
      ])
    )
    .pipe(gulp.dest(paths.assets.dist));
}

/*
 * You could even use `export as` to rename exported tasks
 */
function watchFiles() {
  gulp.watch(paths.stylus.src, gulp.series(stylesTask, browserSyncReload));
  gulp.watch(paths.scripts, gulp.series(scriptsLint, scriptsTask,
    browserSyncReload));
  gulp.watch(paths.pug.src, gulp.series(viewsTask, browserSyncReload));
  gulp.watch(paths.assets.src, gulp.series(assetsTask, browserSyncReload));
}

function fontawesome() {
  return gulp.src('node_modules/@fortawesome/fontawesome-free/webfonts/*')
    .pipe(gulp.dest('./dist/assets/fonts/'));
}

function scriptsVendorTask() {
  return gulp.src(paths.external_scripts)
    .pipe(sourcemaps.init())
    .pipe(uglify())
    .pipe(concat('vendor.min.js'))
    .pipe(sourcemaps.write('maps/'))
    .pipe(gulp.dest(paths.dist))
    .pipe(browsersync.stream());
}

const js = gulp.series(scriptsLint, scriptsTask, scriptsVendorTask);
const build = gulp.series(clean, gulp.parallel(viewsTask, stylesTask,
  assetsTask, js, fontawesome));
const serve = gulp.series(build, gulp.parallel(watchFiles, browserSync));


exports.assets = assetsTask;
exports.styles = stylesTask;
exports.scripts = scriptsTask;
exports.clean = clean;
exports.build = build;
exports.serve = serve;
exports.default = serve;
